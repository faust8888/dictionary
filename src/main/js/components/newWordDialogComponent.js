import React from 'react';
import SkyLight from 'react-skylight';

class NewWordDialogComponent extends React.Component {
    constructor(props){
        super(props);
        this.state = {word: '', translate: '', meaning: '', context: ''};

        this.handleWord = this.handleWord.bind(this);
        this.handleTranslate = this.handleTranslate.bind(this);
        this.handleMeaning = this.handleMeaning.bind(this);
        this.handleContext = this.handleContext.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.saveWord = this.saveWord.bind(this);
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

    handleSubmit(event) {
        this.saveWord();
        this.simpleDialog.hide();
    }

    saveWord() {
        fetch('http://localhost:8080/api/saveWord', {
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
        })
    }

    render() {
        return (
            <div>
                <section>
                    <button onClick={() => this.simpleDialog.show()}>New Word</button>
                </section>
                <SkyLight hideOnOverlayClicked ref={ref => this.simpleDialog = ref} title="Create New Word">
                    <form onSubmit={this.handleSubmit}>
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
                        <input type="submit" value="Save"/>
                    </form>
                </SkyLight>
            </div>
        )
    }
}

NewWordDialogComponent.displayName = 'Example';

export default NewWordDialogComponent;