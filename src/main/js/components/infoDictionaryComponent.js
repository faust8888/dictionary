'use strict';

import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class InfoDictionaryComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {dictionaryName: '', countOfWords: ''};
        this.getDictionaryInfo = this.getDictionaryInfo.bind(this);
    }

    componentWillMount() {
        this.getDictionaryInfo();
    }

    render() {
        return (
            <form>
                <label>Dictionary name: {this.state.dictionaryName}. Count of words: {this.state.countOfWords}</label>
            </form>
        );
    }

    getDictionaryInfo() {
        fetch('http://localhost:8080/api/dictionaryInfo')
            .then((response) => response.json())
            .then((responseJson) => {this.setState({
                dictionaryName: responseJson.dictionaryName, countOfWords:responseJson.countOfWords}); console.log(responseJson)})
            .catch((error) => { console.error(error); });
    }
}

export default InfoDictionaryComponent;