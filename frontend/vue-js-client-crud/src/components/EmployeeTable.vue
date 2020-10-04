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
      <!-- <td v-else>
      <button @click="editMode(employee.id)">Edit</button>
      <button @click="$emit('', employee.id)">Delete</button>
      <button @click="$addreview('', employee.id)">addReview</button>     
      </td> -->
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
        email: '',
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
        console.log(employee.id);
      EmployeeService.delete(employee.id)
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
</style>