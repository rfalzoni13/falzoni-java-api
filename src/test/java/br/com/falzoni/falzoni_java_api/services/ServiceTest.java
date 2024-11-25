package br.com.falzoni.falzoni_java_api.services;

public interface ServiceTest {
    void create_test_success();
    void create_test_failure();
    void update_test_success();
    void update_test_failure();
    void update_test_not_found();
    void delete_test_success();
    void delete_test_not_found();
    void find_test_data();
    void find_test_empty();
    void find_all_test_success();
}
