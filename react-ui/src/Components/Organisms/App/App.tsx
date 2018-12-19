import * as React from 'react';

import AppHeader from "../AppHeader/AppHeader";
import AppSidebar from "../AppSidebar/AppSidebar";

//
// Props
//
interface IAppProps {
    readonly configDocAppName: string;
}

type DefaultProps = Readonly<Required<{
    configDocAppName: string
}>>;

const defaultProps: DefaultProps = {
    configDocAppName: "some configurable app",
};

//
// Class
//
export default class App extends React.PureComponent<IAppProps, any> {
    public static readonly defaultProps = defaultProps;

    public render(): JSX.Element {
        return (
            <div className="app">
                <div className="app--header">
                    {this.renderHeader()}
                </div>
                <div className="app--sidebar">
                    {this.renderSidebar()}
                </div>
                <div className="app--main-content">
                    {this.renderMainContent()}
                </div>
                <div className="app--main-footer">
                    <div className="app-footer">Footer: <a href="https://sandstorm.de/">Sandstorm</a></div>
                </div>
            </div>
        )
    }

    protected renderHeader(): JSX.Element {
        return (<AppHeader/>);
    }

    protected renderSidebar(): JSX.Element {
        return (<AppSidebar/>);
    }

    protected renderMainContent(): JSX.Element {
        return (<span>app</span>);
    }

}
