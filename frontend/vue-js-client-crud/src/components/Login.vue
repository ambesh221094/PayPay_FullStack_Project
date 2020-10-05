<template>
  <div class="submit-form">
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
        type="password"
          class="form-control"
          id="password"
          required
          v-model="employee.password"
          name="password"
        />
      </div>
      <!-- <p class="error" v-if="!loginSuccess">{{ errorMessage }}</p> -->
      <button @click="login" class="btn btn-success">Login</button>
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
    };
  },
  methods: {
    login() {
      var data = {
        employeeName: this.employee.employeeName,
        password: this.employee.password
      };

      EmployeeService.login(data)
        .then(response => {
          localStorage.setItem('LoggedUser',true);
          this.employee.id = response.data.id;
          console.log(response.data);
          if(response.data.admin){
            this.$router.push({name:'admin'})
          }else{
            this.$router.push({name:'employee',params: { name: this.employee.employeeName }})
          }
        })
        .catch(function (error) {
         if (error.response) {
      if(error.response.status=='404'){
       // this.loginSuccess=false;
        //  this.errorMessage='Username or Password is Incorrect';
        this.error.push("Invalid Username or Password");
      }
    }
        });
    },
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}

.error {
    color: red;
}

</style>