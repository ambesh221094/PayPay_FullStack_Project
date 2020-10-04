<template>
  <div id="employee-form">
    <form>
      <label>Employee name</label>
      <input v-model="employee.name" type="text" />
      <label>Employee Password</label>
      <input v-model="employee.password" type="text" />
      <label>Employee Email</label>
      <input v-model="employee.email" type="text" />
      <button @click="saveEmployee">Add Employee</button>
    </form>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";

export default {
  name: 'employee-form',
  data() {
    return {
      employee: {
        name: '',
        password:'',
        email: '',
      },
    }
  },
  methods: {
      saveEmployee() {
      var data = {
        employeeName: this.employee.employeeName,
        employeeEmail: this.employee.employeeEmail,
        password: this.employee.password
      };
      EmployeeService.create(data)
        .then(response => {
          this.employee.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  computed: {
  invalidName() {
    return this.employee.name === ''
  },
  invalidEmail() {
    return this.employee.email === ''
  },
  },
} 
</script>
<style scoped>
  form {
    margin-bottom: 2rem;
  }

  [class*='-message'] {
    font-weight: 500;
  }

  .error-message {
    color: #d33c40;
  }

  .success-message {
    color: #32a95d;
  }
</style>