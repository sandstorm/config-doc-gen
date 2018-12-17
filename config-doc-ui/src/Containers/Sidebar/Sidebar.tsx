import * as React from 'react';
import {connect} from 'react-redux';

import {ApplicationState, selectors} from '../../Redux/Store';
import AppSidebar from "../../Components/Organisms/AppSidebar/AppSidebar";

const mapStateToProps = (state: ApplicationState) => ({
    viewMode: selectors.Ui.ConfigDoc.sidebarViewMode(state),
    selectedItem: selectors.Ui.ConfigDoc.sidebarSelectedItem(state),
});

type EmployeeTableProps = ReturnType<typeof mapStateToProps>;

class Sidebar extends React.PureComponent<EmployeeTableProps> {
    public render(): JSX.Element {
        return (<AppSidebar {...this.props}/>);
    }
}

export default connect(mapStateToProps)(Sidebar);
