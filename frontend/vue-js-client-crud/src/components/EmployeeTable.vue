<template>
  <div id="employee-table">
    <p v-if="employees.length < 1" class="empty-table">No employees</p>
    <table v-else>
      <!-- ...thead... -->
      <tbody>
      <tr>
      <td>Name</td>
      <td>Email</td>
      </tr>
      <tr v-for="employee in employees" :key="employee.id">
      <td>{{employee.employeeName}}</td>
      <td>{{employee.employeeEmail}}</td>
      <button @click="deleteEmployee(employee)">Delete</button>
      <button @click="editEmployee(employee)">Edit</button>
      <button @click="seeReview(employee)" class="button_review" v-if='employee.reviewDone'>See Review</button>
      <button v-else @click="saveReview(employee)">Add Review</button>
      </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";
  export default {
   name: 'employee-table',
   data() {
    return {
        employees: {
        id:'',
        name: '',
        password:'',
        email: ''
      },
   }
  },
  methods: {
      retrieveEmployees() {
      EmployeeService.getAll()
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteEmployee(employee) {
      EmployeeService.delete(employee.id)
        .then(response => {
          this.employees = response.data;
          console.log(response.data.isReviewDone);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveReview(employee){
      this.$router.push({name:'review',params: { id: employee.id }});
    },
    seeReview(employee){
      this.$router.push({name:'review',params: { id: employee.id }});
    }
  },
  beforeMount(){
    this.retrieveEmployees()
 }
  }
</script>

<style scoped>
  button {
    margin: 0 0.5rem 0 0;
    background: #009435;
    border: 1px solid #009435;
  }
  
  .button_review {
    margin: 0 0.5rem 0 0;
    background: #FFFF00;
    border: 1px solid #FFFF00;
  }
</style>