import "./App.css";
import React from "react";
import Header from "./components/common/Header";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";

import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/projects/AddProject";

import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/projects/UpdateProject";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <React.Fragment>
          <Header/>
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
        </React.Fragment>
      </Router>
    </Provider>
  );
}

export default App;
