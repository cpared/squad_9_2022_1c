package com.support.aninfosupportmodule.rest;

import com.support.aninfosupportmodule.dto.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import java.util.List;

@Service
public class EmployeeService {

    private final static String BASE_URL = "https://squad-8-recursos-2022-01.herokuapp.com/empleado";
    private final static String URI_TEMPLATE_EMPLOYEE_BY_ID = "/%7Blegajo%7D?legajo=";
    private final WebClient client = WebClient.create(BASE_URL);

    public Employee getEmployee(Long employeeId) {
        UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        String uri = URI_TEMPLATE_EMPLOYEE_BY_ID + employeeId;
        RequestBodySpec bodySpec = uriSpec.uri(uri);
        return bodySpec.retrieve()
                .bodyToMono(Employee.class).block();
    }

    public List<Employee> getAllEmployees() {
        UriSpec<RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        RequestBodySpec bodySpec = uriSpec.uri(BASE_URL);
        return bodySpec.retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {
                }).block();
    }
}
