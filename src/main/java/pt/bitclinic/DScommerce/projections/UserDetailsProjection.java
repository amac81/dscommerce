package pt.bitclinic.DSCommerce.projections;

public interface UserDetailsProjection {
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}

