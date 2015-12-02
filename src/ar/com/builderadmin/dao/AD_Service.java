package ar.com.builderadmin.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.builderadmin.ldap.vo.UsuarioLDAP_VO;

/**
 * Simple class meant to read a properties file
 * 
 * @author Jaikiran Pai
 * 
 */
public class AD_Service {

	protected static final Logger logger = LoggerFactory
			.getLogger(AD_Service.class);
	
	static final String ldapAdServerSecundario = "172.20.32.3";
	static final String ldapAdServer = "172.20.32.2";
	static final String ldapSearchBase = "dc=help,dc=com,dc=ar";
    
	static final String ldapExtension = "@help.com.ar";
	static final String ldapUsername = "openfire";
	static final String ldapPassword = "345678";
    
	/**
	 * Busca un usuario en base a su username
	 * 
	 * @param accountName
	 * @return
	 */
    public static UsuarioLDAP_VO findAccountByAccountName( String accountName) {

    	DirContext ctx = getContextoAD();
    	
        String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";
        
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String[] atts = {"distinguishedName", "cn", "sAMAccountName", "sn", "objectCategory","memberOf"};
        searchControls.setReturningAttributes(atts);

        NamingEnumeration<SearchResult> results;
        UsuarioLDAP_VO user = null;
		try {
			
			results = ctx.search(ldapSearchBase, searchFilter, searchControls);
			
			SearchResult searchResult = null;
			
			if(results.hasMoreElements()) {
				searchResult = results.nextElement();
				
				user = new UsuarioLDAP_VO(searchResult.getAttributes());
				System.out.println(searchResult.getAttributes());
				
				//make sure there is not another item available, there should be only 1 match
				if(results.hasMoreElements()) {
					System.err.println("Muchos usuarios encontrados con el mismo nombre: " + accountName);
					return null;
				}
			}
			
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
        return user;
    }

    /**
     * Lista todas las personas 
     * 
     * @return
     */
    public static List<UsuarioLDAP_VO> listarTodasLasPersonas() {

    	DirContext ctx = getContextoAD();
    	
    	String searchFilter = "(sAMAccountName=*)";
        
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setCountLimit(10);
        
        String[] atts = {"distinguishedName", "cn", "sAMAccountName", "sn", "objectCategory","memberOf"};
        searchControls.setReturningAttributes(atts);
        
        NamingEnumeration<SearchResult> results;
        
        List<UsuarioLDAP_VO> usuarios= new ArrayList<>();
        UsuarioLDAP_VO user = null;
        
		try {
			results = ctx.search(ldapSearchBase, searchFilter, searchControls);
			SearchResult searchResult = null;
			
//			if(results.hasMoreElements()) {
//				
//				searchResult = results.nextElement();
//				
//				user = new UsuarioLDAP_VO(searchResult.getAttributes());
//				
//				usuarios.add(user);
//				
//			}
			
			
			usuarios.add(findAccountByAccountName("sebastianga"));
			
			
			
//			@SuppressWarnings("rawtypes")
//			NamingEnumeration list = ctx.listBindings(searchFilter);
//
//	    	while (list.hasMore()) {
//	    		Binding  nc = (Binding )list.next();
//	    	    
//	    	    System.out.println(nc);
//	    	}
	    	
			
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
        return usuarios;
    }
    
    /**
     * Autentica usuario y pass contra el AD 
     * 
     * @param usr
     * @param pass
     * @return
     */
    private static Boolean autenticar(String ldapAdServer, String usr, String pass) {
    	Hashtable<String, String> env = new Hashtable<String, String>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://"+ldapAdServer+":389/"); // replace
		env.put(Context.SECURITY_AUTHENTICATION, "simple"); // No other SALS
		env.put(Context.SECURITY_PRINCIPAL, usr+ldapExtension);
		env.put(Context.SECURITY_CREDENTIALS, pass);
		
		try {

			DirContext ctx = new InitialDirContext(env);
			ctx.close();
			
			return true;
			
		} catch (NamingException ex) {

			DAO_Utils.info(logger, "AutenticadorLDAP", "autenticarJAVA", "autenticando", "No se pudo obtener el contexto del LDAP...Credenciales: Usr: "+usr+", Pass: "+pass);
			
			return false;
		}
    }
    
    public static Boolean autenticarConADSecundario(String usr, String pass) {
    	return autenticar(ldapAdServerSecundario, usr, pass);
    }
    
    public static Boolean autenticarConAD(String usr, String pass) {
    	return autenticar(ldapAdServer, usr, pass);
	}

    /**
     * Obtiene una conexion con el AD
     * 
     * @return
     */
	public static DirContext getContextoAD() {
		
		Hashtable<String, String> env = new Hashtable<String, String>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://"+ldapAdServer+":389/"); // replace
		env.put(Context.SECURITY_AUTHENTICATION, "simple"); // No other SALS
		env.put(Context.SECURITY_PRINCIPAL, ldapUsername+ldapExtension);
		env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		
		try {

			return new InitialDirContext(env);
			
		} catch (NamingException ex) {

			DAO_Utils.info(logger, "AutenticadorLDAP", "autenticarJAVA", "active_directory", "No se pudo obtener el contexto del LDAP...");
			
			return null;
		}
		
	}

}