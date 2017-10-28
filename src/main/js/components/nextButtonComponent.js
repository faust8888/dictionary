'use strict';

import update from 'react-addons-update';

const React = require('react');
const ReactDOM = require('react-dom');

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