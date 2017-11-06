'use strict';

import TableComponent from './components/tableComponent'
import NextWordButton from './components/nextButtonComponent'
import PrevWordButton from './components/prevButtonComponent'

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {words: [], wordPosition: 0};
        this.incrementWordPosition = this.incrementWordPosition.bind(this);
        this.decrementWordPosition = this.decrementWordPosition.bind(this);
        this.getNext10Words = this.getNext10Words.bind(this);
    }

    componentWillMount() {
        this.getNext10Words();
    }

    render() {
        if(this.state.words[this.state.wordPosition] !== undefined) {
            return (
                <div>
                    <OneWord oneWord={this.state.words[this.state.wordPosition].word}/>
                    <br/>
                    <PrevWordButton handleClick={this.decrementWordPosition}/>
                    <NextWordButton handleClick={this.incrementWordPosition}/>
                    <br/><br/>
                    <TableComponent words={this.state.words}/>
                </div>
            )
        } else {
            return null;
        }
    }

    incrementWordPosition() {
        var currentWordPosition = this.state.wordPosition;

        if(currentWordPosition == 9) {
            this.getNext10Words();
            this.setState({wordPosition: 0});
        } else {
            var newWordPosition = currentWordPosition + 1;
            this.setState({wordPosition: newWordPosition});
        }
    }

    decrementWordPosition() {
        var position = this.state.wordPosition - 1;
        this.setState({wordPosition: position})
    }

    getNext10Words() {
        fetch('http://localhost:8080/api/next')
            .then((response) => response.json())
            .then((responseJson) => {this.setState({words: responseJson}); console.log(responseJson)})
            .catch((error) => { console.error(error); });
    }
}

export default App;


class OneWord extends React.Component {
    render() {
        return(
            <label>{this.props.oneWord}</label>
        )
    }
}

ReactDOM.render(
	<App />,
    document.getElementById('react')
)


