'use strict';

import React from 'react';
import update from 'react-addons-update';
import ReactDataGrid from 'react-data-grid';
import 'bootstrap/dist/css/bootstrap.css';


const ReactDOM = require('react-dom');

class TableComponent extends React.Component {

    constructor(props) {
        super(props);
        this._columns = [
            {
                key: 'word',
                name: 'Word',
                editable: true
            },
            {
                key: 'translate',
                name: 'Translate',
                editable: true
            },
            {
                key: 'meaning',
                name: 'Meaning',
                editable: true
            },
            {
                key: 'context',
                name: 'Context',
                editable: true
            }
        ];
        this.state = { rows: this.props.words };
        this.rowGetter = this.rowGetter.bind(this);
        this.handleGridRowsUpdated = this.handleGridRowsUpdated.bind(this);
    }

    rowGetter(i) {
        return this.state.rows[i];
    }

    handleGridRowsUpdated({ fromRow, toRow, updated }) {
        if(this.state !== undefined) {
            let rows = this.state.rows;

            for (let i = fromRow; i <= toRow; i++) {
                let rowToUpdate = rows[i];
                let updatedRow = update(rowToUpdate, {$merge: updated});
                rows[i] = updatedRow;
            }

            this.setState({ rows });
        }
    }

    render() {
        return  (
            <ReactDataGrid
                enableCellSelect={true}
                columns={this._columns}
                rowGetter={this.rowGetter}
                rowsCount={this.state.rows.length}
                minHeight={500}
                onGridRowsUpdated={this.handleGridRowsUpdated} />);
    }
}

export default TableComponent;