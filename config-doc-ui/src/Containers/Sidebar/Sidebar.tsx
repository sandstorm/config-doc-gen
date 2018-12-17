import * as React from 'react';
import {connect} from 'react-redux';

import {ApplicationState, selectors} from '../../Redux/Store';

const mapStateToProps = (state: ApplicationState) => ({
    viewMode: selectors.Ui.ConfigDoc.sidebarViewMode(state),
});

type EmployeeTableProps = ReturnType<typeof mapStateToProps>;

class Sidebar extends React.PureComponent<EmployeeTableProps> {
    public render(): JSX.Element {
        return (
            <div className="sidebar">
                <div className="sidebar--view-mode">
                    Sidebar: {this.props.viewMode}
                </div>
                <div className="sidebar--content">
                    Sidebar Content
                </div>
            </div>
        );
    }
}

export default connect(mapStateToProps)(Sidebar);
