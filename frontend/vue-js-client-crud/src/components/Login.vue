<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="employeeName">Name</label>
        <input
          type="text"
          class="form-control"
          id="employeeName"
          required
          v-model="employee.employeeName"
          name="employeeName"
        />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input
          class="form-control"
          id="password"
          required
          v-model="employee.password"
          name="password"
        />
      </div>

      <button @click="saveEmployee" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEmployee">Add</button>
    </div>
  </div>
</template>

<script>
import EmployeeService from "../services/EmployeeService";

export default {
  name: "add-employee",
  data() {
    return {
      employee: {
        id: null,
        employeeName: "",
        password: ""
      },
      submitted: false
    };
  },
  methods: {
    saveEmployee() {
      var data = {
        employeeName: this.employee.employeeName,
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
    },
    
    newEmployee() {
      this.submitted = false;
      this.employee = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>