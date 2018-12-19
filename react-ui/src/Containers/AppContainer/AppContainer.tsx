
import * as React from 'react';

import App from 'src/Components/Organisms/App';
import Sidebar from '../Sidebar';


class AppContainer extends App {

    protected renderSidebar(): JSX.Element {
        return (<Sidebar />);
    }

}

export default AppContainer;
