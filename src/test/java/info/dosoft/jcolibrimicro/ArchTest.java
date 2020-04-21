package info.dosoft.jcolibrimicro;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("info.dosoft.jcolibrimicro");

        noClasses()
            .that()
            .resideInAnyPackage("info.dosoft.jcolibrimicro.service..")
            .or()
            .resideInAnyPackage("info.dosoft.jcolibrimicro.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..info.dosoft.jcolibrimicro.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
