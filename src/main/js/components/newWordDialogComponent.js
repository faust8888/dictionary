import 'babel-polyfill';
import React from 'react';
import {SkyLightStateless} from 'react-skylight';

class NewWordDialogComponent extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            word: '',
            translate: '',
            meaning: '',
            context: '',
            showNewWordDialog: false
        };
    }

    handleWord(event) {
        this.setState({word: event.target.value});
    }

    handleTranslate(event) {
        this.setState({translate: event.target.value});
    }

    handleMeaning(event) {
        this.setState({meaning: event.target.value});
    }

    handleContext(event) {
        this.setState({context: event.target.value});
    }

    tagleNewWordDialog() {
        this.setState({showNewWordDialog: !this.state.showNewWordDialog})
    }

    handleSubmit() {
        this.saveWord();
        this.clearInputFields();
        this.tagleNewWordDialog();
    }

    clearInputFields() {
        this.setState({word: ''})
        this.setState({translate: ''})
        this.setState({meaning: ''})
        this.setState({context: ''})
    }

    async saveWord() {
        var responseAfterSaving = await fetch('http://localhost:8080/api/saveWord', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                word: this.state.word,
                translate: this.state.translate,
                meaning: this.state.meaning,
                context: this.state.context
            })
        });
        const dictionaryInfoJSON = await responseAfterSaving.json();
        this.props.onSave(dictionaryInfoJSON.dictionaryName, dictionaryInfoJSON.countOfWords);
    }

    hideDialog() {
        this.setState({showDialog: false})
    }

    showDialog() {
        this.setState({showDialog: true})
    }

    render() {
        return (
            <div>
                <section>
                    <button onClick={() => this.tagleNewWordDialog()}>New Word</button>
                </section>
                <SkyLightStateless isVisible={this.state.showNewWordDialog} onCloseClicked={() => {this.tagleNewWordDialog()}} title="Create New Word">
                        <label>
                            Word:
                            <input type="text" value={this.state.word} onChange={this.handleWord} />
                        </label>
                        <label>
                            Translate:
                            <input type="text" value={this.state.translate} onChange={this.handleTranslate} />
                        </label>
                        <label>
                            Meaning:
                            <input type="text" value={this.state.meaning} onChange={this.handleMeaning} />
                        </label>
                        <label>
                            Context:
                            <input type="text" value={this.state.context} onChange={this.handleContext} />
                        </label>
                    <button onClick={() => this.handleSubmit()}>Save</button>
                </SkyLightStateless>
            </div>
        )
    }
}

NewWordDialogComponent.displayName = 'Example';

export default NewWordDialogComponent;