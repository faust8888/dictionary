'use strict';

const React = require('react');

class PrevWordButton extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return(
            <button
                className="btn btn-default" onClick={this.props.handleClick}>Prev</button>
        )
    }
}

export default PrevWordButton;
