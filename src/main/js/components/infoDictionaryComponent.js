'use strict';

import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class InfoDictionaryComponent extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <form>
                <label>Dictionary name: {this.props.dictionaryName}. Count of words: {this.props.countOfWords}</label>
            </form>
        );
    }
}

export default InfoDictionaryComponent;