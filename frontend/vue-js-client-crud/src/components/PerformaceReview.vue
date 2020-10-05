<template>
  <div>
    <table align="center">
    <tbody>
    <tr>
    <td colspan="4"><b>{{employees.employeeName}}'s</b> Performance Review</td>
    </tr>
      <tr>
      <td><label>Category</label></td>
      <td><label>Exceeds Expectation</label></td>
      <td><label>Meets Expectation</label></td>
      <td><label>Unsatisfactory</label></td>
      </tr>
      <tr>
      <td><label>Project Delivery</label></td>
      <td><input type="checkbox" value="E"></td>
      <td><input type="checkbox" value="M"></td>
      <td><input type="checkbox" value="U"></td>
      </tr>
      <tr>
      <td><label>Team Communication</label></td>
      <td><input type="checkbox" value="E"></td>
      <td><input type="checkbox" value="M"></td>
      <td><input type="checkbox" value="U"></td>
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
      retrieveEmployeeById() {
      EmployeeService.get(this.$route.params.id)
        .then(response => {
          this.employees = response.data;
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