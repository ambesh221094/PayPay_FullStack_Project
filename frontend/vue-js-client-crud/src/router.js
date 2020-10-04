import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/employees",
      name: "employees",
      component: () => import("./components/Login")
    },
    {
      path: "/employees/:id",
      name: "employee-details",
      component: () => import("./components/Employee")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddEmployee")
    },
    {
      path: "/admin",
      name: "admin",
      component: () => import("./components/AdminView")
    },
    {
      path: "/employee/:name",
      name: "employee",
      component: () => import("./components/EmployeeView")
    }
  ]
});