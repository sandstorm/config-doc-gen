import * as React from 'react';

import AppHeader from "../AppHeader/AppHeader";
import AppSidebar from "../AppSidebar/AppSidebar";

import Namespace from '../../../Domain/Namespace';
import Property from '../../../Domain/Property';

import NamespaceDoc from '../NamespaceDoc';
import PropertyDoc from '../PropertyDoc';

import UiItemView from 'src/Components/Atoms/UiItemView';
import { UiItem } from '../../../Domain/Ui/UiItem';

//
// Props
//
interface IAppProps {
    readonly namespaceProperties: ReadonlyArray<UiItem>;
    readonly selectedNamespace: Namespace | null;
    readonly selectedProperty: Property | null;
    readonly processorVersion: string;
    readonly uiVersion: string;
}

type DefaultProps = Readonly<Required<{
    namespaceProperties: ReadonlyArray<UiItem>;
    selectedNamespace: Namespace | null;
    selectedProperty: Property | null;
    processorVersion: string;
    uiVersion: string;
}>>;

const defaultProps: DefaultProps = {
    namespaceProperties: [],
    processorVersion: "processor-version",
    selectedNamespace: null,
    selectedProperty: null,
    uiVersion: "ui-version",
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
                    {this.props.selectedNamespace == null && this.props.selectedProperty == null ?
                        <div className="app--main-content--nothing-selected">No namespace selected; please select a property or namespace in the sidebar ...</div>
                        : this.renderDoc()
                    }
                </div>
                <div className="app--main-footer">
                    <div className="app-footer">
                        <div className="footer--sponsor-link">
                            Sponsored by: <a href="https://sandstorm.de/">Sandstorm</a>
                        </div>
                        <div className="footer--versions">
                            <span>Processor Version: {this.props.processorVersion}</span>
                            <span>UI Version: {this.props.uiVersion}</span>
                        </div>
                    </div>
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

    private renderDoc() {
        return (
            <div className="app--main-content--inner">
                {this.props.selectedNamespace !== null ?
                    <div className="app--main-content--inner--namespace"><NamespaceDoc namespace={this.props.selectedNamespace} /></div> :
                    ''
                }
                {this.props.selectedProperty !== null ?
                    <div className="app--main-content--inner--property"><PropertyDoc property={this.props.selectedProperty} /></div> :
                    <div className="app--main-content--inner--property-list">
                        <label>All Namespace Properties</label>
                        <ul>
                            {this.props.namespaceProperties.map(property => {
                                return (<li key={property.identifier}><UiItemView item={property} /></li>);
                            })}
                        </ul>
                    </div>
                }
            </div>
        );
    }

}
