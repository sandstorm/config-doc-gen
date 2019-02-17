import * as React from 'react';
import {UiItem} from "../../../Domain/Ui/UiItem";

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {appIcon} from '../../../icons';
import UiItemView from '../../Atoms/UiItemView';

//
// Props
//
interface IAppHeaderProps {
    readonly configDocModuleName: string;
    readonly configDocModuleVersion: string;
    readonly searchTerm: string;
    readonly selectedNamespace: UiItem | null;
    readonly selectedProperty: UiItem | null;

    // actions
    readonly onAppIconClicked: () => void;
    readonly onSearchTermChanged: (searchTerm: string) => void;
}

type DefaultProps = Readonly<Required<{
    configDocModuleName: string;
    configDocModuleVersion: string;
    searchTerm: string;
    selectedNamespace: UiItem | null;
    selectedProperty: UiItem | null;

    onAppIconClicked: () => void;
    onSearchTermChanged: (searchTerm: string) => void;
}>>;

const defaultProps: DefaultProps = {
    configDocModuleName: "module name",
    configDocModuleVersion: "module version",
    searchTerm: "",
    selectedNamespace: null,
    selectedProperty: null,

    // no ops actions
    onAppIconClicked: () => {return},
    onSearchTermChanged: () => { return },
};

//
// Class
//
export default class AppHeader extends React.PureComponent<IAppHeaderProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        const appIconClickedHandler = () => {this.props.onAppIconClicked()};
        const searchTermChangedHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
            this.props.onSearchTermChanged(event.currentTarget.value);
        };
        return <header className="app-header">
            <div className="app-header--icon" onClick={appIconClickedHandler}>
                <FontAwesomeIcon icon={appIcon} />
            </div>
            <div className="app-header--title">
                Config Doc for: {this.props.configDocModuleName}
            </div>
            <div className="app-header--selected-item">
                {this.props.selectedNamespace == null && this.props.selectedProperty == null ?
                    <span>Select a property or namespace!</span> :
                    <div>
                        {this.props.selectedNamespace != null ?
                            <UiItemView item={this.props.selectedNamespace} />
                            : ''
                        }
                        {this.props.selectedProperty != null ?
                            <UiItemView item={this.props.selectedProperty} />
                            : ''
                        }
                    </div>
                }
            </div>
            <div className="app-header--filters">
                <input title="search" onChange={searchTermChangedHandler} placeholder="search ..." />
            </div>
            <div className="app-header--module-version">
                Module version: {this.props.configDocModuleVersion}
            </div>
        </header>;
    }

}
