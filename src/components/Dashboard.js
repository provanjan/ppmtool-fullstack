import React from "react";
import ProjectItem from "./projects/ProjectItem";
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { getProjects } from "../actions/ProjectActions";
import PropTypes from "prop-types";

class Dashboard extends React.Component {
    componentDidMount() {
        this.props.getProjects();
    }
    render() {
        const { projects } = this.props.projects;
        console.log(projects);
        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Projects</h1>
                            <br />
                            <Link to="/addProject" className="btn btn-lg btn-info">
                                Create a Project
                            </Link>
                            <br />
                            <hr />
                            {projects.map((project) => {
                                return (
                                    <ProjectItem key={project.id} project={project} />
                                );
                            })}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Dashboard.propTypes = {
    getProjects: PropTypes.func.isRequired,
    projects: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
    projects: state.projects,
});

export default connect(mapStateToProps, { getProjects })(Dashboard);