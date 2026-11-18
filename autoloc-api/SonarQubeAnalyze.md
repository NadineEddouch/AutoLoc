# Impact Faible
* Remove the 'ApiResponses' wrapper from this annotation group [java:S1710] - CORRIGÉ
* Remove this "@ExtendWith" annotation, already implied by "@SpringBootTest". [java:S9341] - CORRIGÉ
* Remove this unnecessary cast to "List". [java:S1905] - CORRIGÉ (TODO ajouté)
* Remove this unused import 'io.swagger.v3.oas.annotations.responses.ApiResponse'. [java:S1128] - CORRIGÉ
* Remove this unused import 'io.swagger.v3.oas.annotations.responses.ApiResponses'. [java:S1128] - CORRIGÉ
* Immediately return this expression instead of assigning it to the temporary variable "rapport". [java:S1488] - CORRIGÉ
* Remove this unused "vehiculeService" private field. [java:S1068] - CORRIGÉ

# Impact Moyen
* Rename this generic name to match the regular expression '^[A-Z][0-9]?$'. [java:S119] - CORRIGÉ
* Explicitly specify the time zone by passing a ZoneId or a Clock to the .now() method. [java:S8688] - CORRIGÉ
* Add a private constructor to hide the implicit public one. [java:S1118] - CORRIGÉ

# Impact Important
* Replace this use of System.out by a logger. [java:S106] - NON CORRIGÉ
* Replace this persistent entity with a simple POJO or DTO object. [java:S4684] - NON CORRIGÉ
* Add at least one assertion to this test case. [java:S2699] - NON CORRIGÉ


