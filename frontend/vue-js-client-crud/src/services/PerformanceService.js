import http from "./../http-common";

class PerformanceService {
 
  create(data) {
    return http.post("/performanceReview/add", data,{headers: {'Content-Type': 'application/json'}});
  }
}

export default new PerformanceService();