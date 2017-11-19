'use strict';

const React = require('react');

class NextWordButton extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return(
            <button
                className="btn btn-default" onClick={this.props.handleClick}>Next</button>
        )
    }
}

export default NextWordButton;