'use strict';

import 'babel-polyfill';
import TableComponent from './components/tableComponent'
import NextWordButton from './components/nextButtonComponent'
import PrevWordButton from './components/prevButtonComponent'
import NewWordDiaogComponent from './components/newWordDialogComponent'
import InfoDictionaryComponent from './components/infoDictionaryComponent'

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {words: [], wordPosition: 0, dictionaryName: '', countOfWords: ''};
        this.loadDictionaryInfo = this.loadDictionaryInfo.bind(this);
        this.updateDictionaryInfo = this.updateDictionaryInfo.bind(this);
        this.incrementWordPosition = this.incrementWordPosition.bind(this);
        this.decrementWordPosition = this.decrementWordPosition.bind(this);
        this.getNext10Words = this.getNext10Words.bind(this);
    }

    render() {
        if(this.state.words[this.state.wordPosition] !== undefined) {
            return (
                <div>
                    <OneWord oneWord={this.state.words[this.state.wordPosition].word}/>
                    <br/>
                    <PrevWordButton handleClick={this.decrementWordPosition}/>
                    <NextWordButton handleClick={this.incrementWordPosition}/>
                    <br/>
                    <InfoDictionaryComponent dictionaryName={this.state.dictionaryName} countOfWords={this.state.countOfWords}/>
                    <br/>
                    <NewWordDiaogComponent onSave={this.updateDictionaryInfo}/>
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

    componentWillMount() {
        this.getNext10Words();
        this.loadDictionaryInfo();
    }

    getNext10Words() {
        fetch('http://localhost:8080/api/next')
            .then((words) => words.json())
            .then((wordsJSON) => {this.setState({words: wordsJSON})})
            .catch((error) => { console.error(error); });
    }

    updateDictionaryInfo(dictionaryName, countOfWords) {
        this.setState({dictionaryName: dictionaryName, countOfWords: countOfWords})
    }

    loadDictionaryInfo() {
        fetch('http://localhost:8080/api/dictionaryInfo')
            .then((dictionaryInfo) => dictionaryInfo.json())
            .then((dictionaryInfoJSON) =>
                {this.setState(
                     {dictionaryName: dictionaryInfoJSON.dictionaryName, countOfWords: dictionaryInfoJSON.countOfWords}
                )})
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


