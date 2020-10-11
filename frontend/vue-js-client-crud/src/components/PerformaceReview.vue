<template>
  <div>
    <table align="center">
      <tbody>
        <tr>
          <td colspan="4">
            <b>{{employees.employeeName}}'s</b> Performance Review
          </td>
        </tr>
        <tr>
          <td>
            <label>
              <b>Category</b>
            </label>
          </td>
          <td>
            <label>Review Given</label>
          </td>
        </tr>
        <tr>
          <td>
            <label>
              <b>Project Delivery</b>
            </label>
          </td>
          <td v-if="employees.reviewDone">
            <label>{{performance.projectDelivery}}</label>
          </td>
          <td v-else>
            <select v-model="performance.projectDelivery">
              <option disabled value>Please select one</option>
              <option value="Meets Expectations">Meets Expectations</option>
              <option value="Exceed Expectations">Exceed Expectations</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <label>
              <b>Team Communication</b>
            </label>
          </td>
          <td v-if="employees.reviewDone">
            <label>{{performance.teamComm}}</label>
          </td>
          <td v-else>
            <select v-model="performance.teamComm">
              <option disabled value>Please select one</option>
              <option value="Meets Expectations">Meets Expectations</option>
              <option value="Exceed Expectations">Exceed Expectations</option>
            </select>
          </td>
        </tr>
        <tr height="100px">
          <td>
            <label>
              <b>Admin's Feedback</b>
            </label>
          </td>
          <td>
            <textarea :readonly="employees.reviewDone" rows="7" cols="50" v-model="performance.description"></textarea>
          </td>
        </tr>
        <tr v-for="f in feedback" :key="f.id" height="100px">
          <td>
            <label v-if="!f.feedbackDone">
              <b>Add your feedback</b>
            </label>
            <label v-else>
              <div v-for="employee in employeeOthers" :key="employee.id">
              <b>{{employee.employeeName}}'s Feedback</b>
              </div>
            </label>
          </td>
          <td>
            <textarea :readonly="f.feedbackDone" rows="7" cols="50" v-model="f.feedback"></textarea>
          </td>
        </tr>
        <tr v-if="!employees.reviewDone">
          <td>
            <label><b>Users assigned for participation in feedback process</b></label>
          </td>
          <td>
            <ul>
              <div v-for="employee in employeeOthers" :key="employee.id">
              <li>{{employee.employeeName}} <input type="checkbox" :value=employee.id v-model="performance.users"></li>
              </div>
            </ul>
          </td>
        </tr>
        <tr v-if="!employees.reviewDone">
          <td colspan="4">
            <button @click="savePerformanceReview">Submit</button>
          </td>
        </tr>
        <div v-for="f in feedback" :key="f.id">
        <tr v-if="!f.feedbackDone">
          <td colspan="4">
            <button @click="saveFeedback(employee)">Submit</button>
          </td>
        </tr>
        </div>
      </tbody>
    </table>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";
import PerformanceService from "../services/PerformanceService";
export default {
  name: "employee-table",
  data() {
    return {
      performance: {
        id: "",
        empId: "",
        projectDelivery: "",
        teamComm: "",
        description: "",
        users:[]
      },
      employees: {
        id: "",
        name: "",
        password: "",
        email: ""
      },
      employeeOthers: {
        id: "",
        name: "",
        password: "",
        email: ""
      },
      feedback:{
        empId:"",
        description:"",
        empFeedBackPending:"",
        feedbackDone:"",
        feedback:""
      }
    };
  },
  methods: {
    savePerformanceReview() {
      var data = {
        empId: this.$route.params.id,
        projectDelivery: this.performance.projectDelivery,
        teamComm: this.performance.teamComm,
        description: this.performance.description,
        userIds:this.performance.users
      };
      PerformanceService.create(data)
        .then(response => {
          this.performance = response.data;
          this.$router.push({ name: "admin" });
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveEmployeeById() {
      EmployeeService.get(this.$route.params.id)
        .then(response => {
          this.employees = response.data;
          if (this.employees.reviewDone) {
            this.retrievePerformanceById();
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrievePerformanceById() {
      PerformanceService.get(this.$route.params.id)
        .then(response => {
          this.performance = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveOtherEmployees() {
      EmployeeService.getOther(this.$route.params.id)
        .then(response => {
          this.employeeOthers = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveFeedback() {
      var data = {
        empFeedBackPending: this.$route.params.id,
        feedback: this.feedback.description,
      };
      PerformanceService.addFeedback(data)
        .then(response => {
          this.performance = response.data;
          this.$router.push({name:'employee',params: { id: performance.id }});
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveFeedback() {
      PerformanceService.getFeedbackDone(this.$route.params.id)
        .then(response => {
          this.feedback = response.data;
          console.log(response.data.empFeedBackPending)
        })
        .catch(e => {
          console.log(e);
        });
    },
  },
  beforeMount() {
    this.retrieveEmployeeById();
    this.retrieveOtherEmployees();
    this.retrieveFeedback();
  }
};
</script>

<style scoped>
table,
th,
td {
  border: 1px solid black;
  text-align: center;
}
</style>