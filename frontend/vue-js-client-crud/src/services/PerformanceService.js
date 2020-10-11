import http from "./../http-common";

class PerformanceService {
 
  create(data) {
    console.log(data);
    return http.post("/performanceReview/add", data,{headers: {'Content-Type': 'application/json'}});
  }

  get(id) {
    return http.get(`/performance/${id}`);
  }
}

export default new PerformanceService();