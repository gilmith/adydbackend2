package org.jacobo.adyd.infraestructure.entities.validator;

//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;

class SortValidatorTest {

//    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//    private final CampaignRepositoryAdapter adapter = new CampaignRepositoryAdapter(null, null);
//
//    private Method findAll() throws NoSuchMethodException {
//        return CampaignRepositoryAdapter.class.getMethod("findAll", PageRequest.class);
//    }
//
//    private Page<Object> page(Sort sort) {
//        return new PageImpl<>(List.of(), PageRequest.of(0, 10, sort), 0);
//    }
//
//    @Test
//    void rejectsUnknownSortProperty() throws Exception {
//        var violations = validator.forExecutables().validateReturnValue(
//                adapter, findAll(), page(Sort.by(Sort.Direction.ASC, "noExiste")));
//        assertTrue(!violations.isEmpty(), "debe rechazar campo desconocido");
//    }
//
//    @Test
//    void acceptsValidSortProperty() throws Exception {
//        var violations = validator.forExecutables().validateReturnValue(
//                adapter, findAll(), page(Sort.by(Sort.Direction.ASC, "name")));
//        assertTrue(violations.isEmpty());
//    }
//
//    @Test
//    void acceptsUnsorted() throws Exception {
//        var violations = validator.forExecutables().validateReturnValue(
//                adapter, findAll(), page(Sort.unsorted()));
//        assertTrue(violations.isEmpty());
//    }
}
