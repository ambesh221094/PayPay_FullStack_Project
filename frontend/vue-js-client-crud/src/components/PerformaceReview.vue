<template>
  <div>
    <table align="center">
    <tbody>
    <tr>
    <td colspan="4"><b>{{employees.employeeName}}'s</b> Performance Review</td>
    </tr>
      <tr>
      <td><label><b>Category</b></label></td>
      <td><label><b>Exceeds Expectation</b></label></td>
      <td><label><b>Meets Expectation</b></label></td>
      <td><label><b>Unsatisfactory</b></label></td>
      </tr>
      <tr>
      <td><label><b>Project Delivery</b></label></td>
      <td><input v-model="performance.projectDeliveryE" type="checkbox" value="E" ></td>
      <td><input v-model="performance.projectDeliveryM" type="checkbox" value="M"></td>
      <td><input v-model="performance.projectDeliveryU" type="checkbox" value="U"></td>
      </tr>
      <tr>
      <td><label><b>Team Communication</b></label></td>
      <td><input v-model="performance.teamCommE" type="checkbox" value="E"></td>
      <td><input v-model="performance.teamCommM" type="checkbox" value="M"></td>
      <td><input v-model="performance.teamCommU" type="checkbox" value="U"></td>
      </tr>
      <tr>
      <td colspan="4"><button @click="savePerformanceReview">Submit</button></td>
      </tr>
    </tbody>
    </table>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";
import PerformanceService from "../services/PerformanceService";
  export default {
   name: 'employee-table',
   data() {
    return {
        performance: {
        id:'',
        empId: '',
        projectDelivery:'',
        teamComm: ''
      },
      employees: {
        id:'',
        name: '',
        password:'',
        email: '',
      },
   }
  },
  methods: {
      savePerformanceReview() {
      PerformanceService.create()
        .then(response => {
          this.performance = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    retrieveEmployeeById() {
      EmployeeService.get(this.$route.params.id)
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
    },
    beforeMount(){
    this.retrieveEmployeeById()
  }
  } 
</script>

<style scoped>
  table, th, td {
    border: 1px solid black;
    text-align:center;
  }
</style>