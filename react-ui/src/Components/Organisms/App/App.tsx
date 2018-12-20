import * as React from 'react';

import AppHeader from "../AppHeader/AppHeader";
import AppSidebar from "../AppSidebar/AppSidebar";

import Namespace from '../../../Domain/Namespace';
import Property from '../../../Domain/Property';

import NamespaceDoc from '../NamespaceDoc';
import PropertyDoc from '../PropertyDoc';

//
// Props
//
interface IAppProps {
    readonly selectedNamespace: Namespace | null;
    readonly selectedProperty: Property | null;
}

type DefaultProps = Readonly<Required<{
    selectedNamespace: Namespace | null;
    selectedProperty: Property | null;
}>>;

const defaultProps: DefaultProps = {
    selectedNamespace: null,
    selectedProperty: null,
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
                    <div className="app--main-content--inner">
                        {this.props.selectedNamespace !== null ?
                            <div className="app--main-content--inner--namespace"><NamespaceDoc namespace={this.props.selectedNamespace} /></div> :
                            <div className="app--main-content--inner--no-namespace">No namespace selected; please select a property or namespace in the sidebar ...</div>
                        }
                        {this.props.selectedProperty !== null ?
                            <div className="app--main-content--inner--property"><PropertyDoc property={this.props.selectedProperty} /></div> :
                            <div className="app--main-content--inner--no-property">No property selected; please select a property or namespace in the sidebar ...</div>
                        }
                    </div>
                </div>
                <div className="app--main-footer">
                    <div className="app-footer">Footer: <a href="https://sandstorm.de/">Sandstorm</a></div>
                </div>
            </div>
        )
    }

    protected renderHeader(): JSX.Element {
        return (<AppHeader />);
    }

    protected renderSidebar(): JSX.Element {
        return (<AppSidebar />);
    }

}
