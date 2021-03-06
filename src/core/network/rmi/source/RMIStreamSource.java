/**
 * 
 */
package core.network.rmi.source;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Logger;

import core.element.IElement;

/**
 * @author Roland
 *
 */
public class RMIStreamSource extends UnicastRemoteObject implements IRMIStreamSource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7442458055103865656L;
	
	private int port;
	private IElement[] chunk;
	private ArrayList<String> attrNames;
	private Registry registry;
	private static final Logger logger = Logger.getLogger("RMIStreamSource");
	
	public RMIStreamSource(int port) throws RemoteException {
		super(port);
		this.setPort(port);
		this.registry = LocateRegistry.createRegistry(this.getPort());
	}
	
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	
	/* (non-Javadoc)
	 * @see core.network.rmi.IRMISource#getInputStream()
	 */
	@Override
	public IElement[] getInputStream() throws RemoteException {
		return this.chunk;
	}

	/**
	 * @return the attrNames
	 */
	@Override
	public ArrayList<String> getAttrNames() throws RemoteException{
		return attrNames;
	}

	/**
	 * @param attrNames the attrNames to set
	 */
	public void setAttrNames(ArrayList<String> attrNames) {
		this.attrNames = attrNames;
	}

	public void cast(IElement[] chunk, ArrayList<String> attrNames){
		this.chunk = chunk;
		this.setAttrNames(attrNames);
		try {
			registry.bind("tuples", (IRMIStreamSource)this);
		} catch (RemoteException | AlreadyBoundException e) {
			logger.info("Server unable to bind the remote object");
			logger.info("Re-sending chunk...");
			try {
				Thread.sleep(1000);
				this.cast(chunk, attrNames);
			} catch (InterruptedException e1) {
				logger.info("Waiting for client acknowlegment before sending new tuples...");
			}
			
		}
	}
	
	@Override
	public void releaseRegistry() throws RemoteException{
		try {
			UnicastRemoteObject.unexportObject(registry, true);
		} catch (NoSuchObjectException e) {
			logger.severe("There is no registry to release on given host/port");
		}
	}
}
