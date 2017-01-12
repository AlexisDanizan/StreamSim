/**
 * 
 */
package servlets;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ElementStreamBean;
import core.profile.IStreamProfile;
import core.stream.ElementStream;
import core.stream.IElementStream;
import core.transition.IStreamTransition;

/**
 * @author Roland
 *
 */
public class Generator extends HttpServlet {

	ServletContext context;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8800848739710775121L;

	/**
	 * 
	 */
	public Generator() {
	}

	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.context = config.getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/Generator.jsp").forward(req, resp);
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String refresh = (String) req.getParameter("refreshGenerator");
		
		if(refresh != null){
			ElementStreamBean stream = (ElementStreamBean) req.getSession().getAttribute("stream");
			if(stream != null){
				IElementStream eStream = stream.getStream();
				if(eStream != null){
					eStream.getSource().releaseRegistry();
				}
			}
			stream = new ElementStreamBean();
			req.getSession().setAttribute("stream", stream);
			
			ArrayList<String> schemas = Generator.getStreamList(this.getServletContext().getRealPath("/schemas"));
			req.setAttribute("schemas", schemas);
			this.getServletContext().getRequestDispatcher("/Generator.jsp").forward(req, resp);
		}
		
		String load = (String) req.getParameter("load");
		
		if(load != null){
			String streamName = (String) req.getParameter("name");
			Integer port = Integer.parseInt((String) req.getParameter("port"));
			String variation = (String) req.getParameter("variation");

			ElementStreamBean bean = (ElementStreamBean) req.getSession().getAttribute("stream");
			ElementStream stream;
			try {
				stream = new ElementStream(port, streamName, variation, this.context);
				stream.initializeSchema();
				stream.initializeVariations();

				ArrayList<String> attributes = stream.getAttributeNames();
				ArrayList<String> attrTypes = new ArrayList<>();
				Integer nbAttrs = attributes.size();
				for(int i = 0; i < nbAttrs; i++){
					attrTypes.add(stream.getAttributeType(attributes.get(i)));
				}

				HashMap<String, Double> variations = new HashMap<>();
				ArrayList<IStreamProfile> profiles = stream.getProfiles();
				int nbProfiles = profiles.size();

				ArrayList<IStreamTransition> transitions = stream.getTransitions();
				int nbTransitions = transitions.size();

				for(int i = 0; i < nbProfiles; i++){
					String profile = "profile" + i;
					variations.put(profile, profiles.get(i).getDuration());
				}
				for(int j = 0; j < nbTransitions; j++){
					String transition = "transition" + j;
					variations.put(transition, transitions.get(j).getDuration());
				}

				bean.setStream(stream);
				bean.setName(streamName);
				bean.setPort(port);
				bean.setNbAttrs(nbAttrs);
				bean.setVariation(variation);
				bean.setAttrNames(attributes);
				bean.setAttrTypes(attrTypes);
				bean.setVariations(variations);

				String live = (String) req.getParameter("live");
				if(live != null){
					this.getServletContext().getRequestDispatcher("/LiveControl.jsp").forward(req, resp);
				}else{
					this.getServletContext().getRequestDispatcher("/Generator.jsp").forward(req, resp);
				}
			} catch (URISyntaxException e) {
				String errorMessage = "Unable to load the stream schema/variation because of " + e + "\n";
				errorMessage += "Please try again with an existing stream";
				req.setAttribute("error", errorMessage);
				this.getServletContext().getRequestDispatcher("/Generator.jsp");
			}
		}
	}
	
	public static ArrayList<String> getStreamList(String schemaPath){
		ArrayList<String> result = new ArrayList<>();
		File schemaFolder = new File(schemaPath);
		File[] schemas = schemaFolder.listFiles();
		if(schemas != null){
			for(File schema : schemas){
				String schemaName = schema.getName().split("Schema")[0];
				result.add(schemaName);
			}
		}
		return result;
	}
}