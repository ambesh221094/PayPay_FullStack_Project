<template>
  <div id="employee-form">
    <form>
      <label>Employee name</label>
      <input v-model="employee.name" type="text" />
      <label>Employee Password</label>
      <input v-model="employee.password" type="password" />
      <label>Employee Email</label>
      <input v-model="employee.email" type="text" />
      <button @click="saveEmployee">Add Employee</button>
    </form>
    <EmployeeTable/>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";
import EmployeeTable from "../components/EmployeeTable";
export default {
  name: 'employee-form',
  components: {
    EmployeeTable,
  },
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
        employeeName: this.employee.name,
        employeeEmail: this.employee.email,
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

  button {
    background: #009435;
    border: 1px solid #009435;
  }
</style>