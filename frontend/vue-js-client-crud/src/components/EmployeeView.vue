<template>
  <div>
    <p v-if="employees.length < 1" class="empty-table">No Performance Review for feedback</p>
    <table v-else align="center">
      <tbody>
        <tr>
          <td colspan="4">
            <b>List Of Employee's Performance Review needs your feedback</b>
          </td>
        </tr>
        <tr v-for="employee in employees" :key="employee.id">
          <td>
            <label>
              <b>{{employee.employeeName}}</b>
            </label>
            <button @click="giveFeedback(employee)">Give Feedback</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import EmployeeService from "../services/EmployeeService";

export default {
  data() {
    return {
      employees: {
        id: "",
        name: "",
        password: "",
        email: ""
      }
    };
  },
  methods: {
    retrieveEmployeePendingFeedBack() {
      console.log(this.$route.params.id);
      EmployeeService.getPending(this.$route.params.id)
        .then(response => {
          this.employees = response.data;
          console.log(response.data)
        })
        .catch(e => {
          console.log(e);
        });
    },
    giveFeedback(employee){
      this.$router.push({name:'review',params: { id: employee.id }});
    }
  },
  beforeMount() {
    this.retrieveEmployeePendingFeedBack();
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