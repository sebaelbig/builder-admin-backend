package ar.org.hospitalespanol.ldap.controllers;

import org.springframework.stereotype.Controller;

/**
 * Default controller.
 * 
 * @author Mattias Hellborg Arthursson
 */
@Controller
public class DefaultController {

	// @Autowired
	// private LdapTreeBuilder ldapTreeBuilder;
	//
	// @Autowired
	// private PersonDao personDao;
	//
	// @RequestMapping("/welcome.do")
	// public void welcomeHandler() {
	// }
	//
	// @RequestMapping("/showTree.do")
	// public ModelAndView showTree() {
	// LdapTree ldapTree = ldapTreeBuilder.getLdapTree(LdapUtils
	// .emptyLdapName());
	// HtmlRowLdapTreeVisitor visitor = new PersonLinkHtmlRowLdapTreeVisitor();
	// ldapTree.traverse(visitor);
	// return new ModelAndView("showTree", "rows", visitor.getRows());
	// }
	//
	// @RequestMapping("/addPerson.do")
	// public String addPerson() {
	// Person person = getPerson();
	//
	// personDao.create(person);
	// return "redirect:/showTree.do";
	// }
	//
	// @RequestMapping("/updatePhoneNumber.do")
	// public String updatePhoneNumber() {
	// Person person = personDao.findByPrimaryKey("Sweden", "company1",
	// "John Doe");
	// person.setPhone(StringUtils.join(new String[] { person.getPhone(), "0"
	// }));
	//
	// personDao.update(person);
	// return "redirect:/showTree.do";
	// }
	//
	// @RequestMapping("/removePerson.do")
	// public String removePerson() {
	// Person person = getPerson();
	//
	// personDao.delete(person);
	// return "redirect:/showTree.do";
	// }
	//
	// @RequestMapping("/showPerson.do")
	// public ModelMap showPerson(String country, String company, String
	// fullName) {
	// Person person = personDao.findByPrimaryKey(country, company, fullName);
	// return new ModelMap("person", person);
	// }
	//
	// private Person getPerson() {
	// Person person = new Person();
	// person.setFullName("John Doe");
	// person.setLastName("Doe");
	// person.setCompany("company1");
	// person.setCountry("Sweden");
	// person.setDescription("Test user");
	// return person;
	// }
	//
	// /**
	// * Generates appropriate links for person leaves in the tree.
	// *
	// * @author Mattias Hellborg Arthursson
	// */
	// private static final class PersonLinkHtmlRowLdapTreeVisitor extends
	// HtmlRowLdapTreeVisitor {
	//
	// @Override
	// protected String getLinkForNode(DirContextOperations node) {
	// String[] objectClassValues = node
	// .getStringAttributes("objectClass");
	// if (containsValue(objectClassValues, "person")) {
	// Name dn = node.getDn();
	// String country = encodeValue(LdapUtils.getStringValue(dn, "c"));
	// String company = encodeValue(LdapUtils.getStringValue(dn, "ou"));
	// String fullName = encodeValue(LdapUtils
	// .getStringValue(dn, "cn"));
	//
	// return "showPerson.do?country=" + country + "&company="
	// + company + "&fullName=" + fullName;
	// } else {
	// return super.getLinkForNode(node);
	// }
	// }
	//
	// private String encodeValue(String value) {
	// try {
	// return URLEncoder.encode(value, "UTF8");
	// } catch (UnsupportedEncodingException e) {
	// // Not supposed to happen
	// throw new RuntimeException("Unexpected encoding exception", e);
	// }
	// }
	//
	// private boolean containsValue(String[] values, String value) {
	// for (String oneValue : values) {
	// if (StringUtils.equals(oneValue, value)) {
	// return true;
	// }
	// }
	// return false;
	// }
	// }

//	private LdapTemplate ldapTemplate;
//
//	private class PersonAttributesMapper implements AttributesMapper<UsuarioLDAP> {
//
//		@Override
//		public UsuarioLDAP mapFromAttributes(Attributes attrs)
//				throws NamingException {
//
//			UsuarioLDAP person = new UsuarioLDAP();
//			
//			person.setFullName((String) attrs.get("cn").get());
//			person.setLastName((String) attrs.get("sn").get());
//			person.setDescription((String) attrs.get("description").get());
//			
//			return person;
//		}
//	}
//
//	public List<UsuarioLDAP> getAllPersons() {
//
//		return ldapTemplate.search(query().where("objectclass").is("person"),
//				new PersonAttributesMapper());
//	}
//
//	public UsuarioLDAP findPerson(String dn) {
//		return ldapTemplate.lookup(dn, new PersonAttributesMapper());
//	}
//	
//	 public List<String> getPersonNamesByLastName(String lastName) {
//
//	      LdapQuery query = query()
//	         .base("dc=261consulting,dc=com")
//	         .attributes("cn", "sn")
//	         .where("objectclass").is("person")
//	         .and("sn").is(lastName);
//
//	      return ldapTemplate.search(query,
//	         new AttributesMapper<String>() {
//	    	  
//	            @Override
//				public String mapFromAttributes(Attributes attrs)
//	               throws NamingException {
//
//	               return (String) attrs.get("cn").get();
//	               
//	            }
//	         });
//	   }
}
