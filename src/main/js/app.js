'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {words: []};
    }

    componentWillMount() {
        fetch('http://localhost:8080/api/next')
            .then((response) => response.json())
            .then((responseJson) => {this.setState({words: responseJson}); console.log(responseJson)})
            .catch((error) => { console.error(error); });

    }

    render() {
        return (
			<EmployeeList words={this.state.words}/>
        )
    }
}

class EmployeeList extends React.Component{

    constructor(props) {
        super(props);
        this._myHandler = this._myHandler.bind(this);
    }

    _myHandler(props) {
        console.log(props);
    }

    render() {
        var words = this.props.words.map(oneWord => <Employee key={oneWord.word} word={oneWord}/>);
        return (
			<table>
				<tbody>
				<tr>
					<th>Word</th>
					<th>Meaning</th>
					<th>Translate</th>
					<th>Context</th>
				</tr>
                {words}
				</tbody>
			</table>
        )
    }
}


class Employee extends React.Component{
    render() {
        return (
			<tr>
				<td>{this.props.word.word}</td>
				<td>{this.props.word.meaning}</td>
				<td>{this.props.word.translate}</td>
				<td>{this.props.word.context}</td>
			</tr>
        )
    }
}

ReactDOM.render(
	<App />,
    document.getElementById('react')
)


