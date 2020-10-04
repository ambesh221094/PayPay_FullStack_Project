import http from "./../http-common";

class EmployeeService {
  getAll() {
    return http.get("/employees");
  }

  get(id) {
    return http.get(`/employees/${id}`);
  }

  login(data) {
    return http.post("/employees", data,{headers: {'Content-Type': 'application/json'}});
  }

  create(data) {
    return http.post("/employees/add", data,{headers: {'Content-Type': 'application/json'}});
  }

  update(id, data) {
    return http.put(`/employees/${id}`, data);
  }

  delete(id) {
    return http.delete(`/employees/${id}`);
  }

  deleteAll() {
    return http.delete(`/employees`);
  }

  findByTitle(title) {
    return http.get(`/employees?title=${title}`);
  }
}

export default new EmployeeService();