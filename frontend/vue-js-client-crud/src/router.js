import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

function guardMyroute(to, from, next)
{
 var isAuthenticated= false;
if(localStorage.getItem('LoggedUser'))
  isAuthenticated = true;
 else
  isAuthenticated= false;
 if(isAuthenticated) 
 {
  next();
 } 
 else
 {
  next('/');
}
}


export default new Router({

  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/login",
      name: "login",
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
      beforeEnter : guardMyroute,
      component: () => import("./components/AdminView")
    },
    {
      path: "/employee/:name/:id",
      name: "employee",
      beforeEnter : guardMyroute,
      component: () => import("./components/EmployeeView")
    },
    {
      path: "/review/:id",
      name: "review",
      beforeEnter : guardMyroute,
      component: () => import("./components/PerformaceReview")
    }
  ]
});

