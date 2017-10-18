## Servicio REST de personas utilizando Jax-RS 2.0 (Jersey)

Clonar repositorio y generar WAR para su posterior deploy en WildFly 11

````
  mvn -B -Dmaven.test.skip=true -DskipTests install
 ````
 
 Se utilizo JerseyTest para pruebas de integracion. Ver [PersonTest](https://github.com/FacuLbsz/jaxrs-xample/blob/master/src/test/java/jaxrs/person/PersonTest.java)
 
 ````
  mvn test
 ````
 
 
  
