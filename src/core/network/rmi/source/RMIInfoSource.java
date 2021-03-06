/**
 * 
 */
package core.network.rmi.source;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Roland
 *
 */
public class RMIInfoSource extends UnicastRemoteObject implements IRMIInfoSource {

	ArrayList<String> info;
	
	private int port;
	private Registry registry;
	private static final Logger logger = Logger.getLogger("RMIInfoSource");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5844687925266614589L;

	public RMIInfoSource(int port) throws RemoteException {
		super(port);
		this.port = port;
		this.registry = LocateRegistry.createRegistry(this.port);
		this.info = new ArrayList<>();
	}

	@Override
	public ArrayList<String> getInfo() throws RemoteException{
		return this.info;
	}
	
	public void setInfo(ArrayList<String> info){
		this.info = info;
	}
	
	public void cast(){
		try {
			registry.rebind("info", this);
		} catch (RemoteException e) {
			logger.info("Server unable to bind the remote object");	
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
