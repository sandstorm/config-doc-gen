import * as React from 'react';
import {findIconDefinition, IconDefinition} from '@fortawesome/fontawesome-svg-core'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import './_App.scss';
import AppSidebar from "../AppSidebar/AppSidebar";

const appIcon: IconDefinition = findIconDefinition({prefix: 'fas', iconName: 'info'});
//
// Props
//
interface AppProps {
    readonly configDocAppName: string;
    readonly sidebar: JSX.Element;
}

type DefaultProps = Readonly<Required<{
    configDocAppName: string,
    sidebar: JSX.Element
}>>;

const defaultProps: DefaultProps = {
    configDocAppName: "some configurable app",
    sidebar: <AppSidebar/>,
};

//
// State
//
interface AppState {
}

const initialAppState: AppState = {};

//
// Class
//
export default class App extends React.PureComponent<AppProps, AppState> {
    public static readonly defaultProps = defaultProps;

    public constructor(props: AppProps) {
        super(props);
        this.state = initialAppState;
    }

    public render(): JSX.Element {
        return (
            <div className="app">
                <header className="app--header">
                    <div className="app--header--icon">
                        <FontAwesomeIcon icon={appIcon}/>
                    </div>
                    Header - App: {this.props.configDocAppName}
                </header>
                <div className="sidebar-container">
                    {[this.props.sidebar]}
                </div>
            </div>
        )
    }
}
