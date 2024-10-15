package pt.bitclinic.javaaccommerce.projections;

public interface UserDetailsProjection {
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}

