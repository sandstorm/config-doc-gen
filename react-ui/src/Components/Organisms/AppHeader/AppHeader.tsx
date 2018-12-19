import * as React from 'react';
import { UiItem } from "../../../Domain/Ui/UiItem";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { appIcon } from '../../../icons';
import UiItemView from '../../Atoms/UiItemView';

//
// Props
//
interface IAppHeaderProps {
    readonly configDocAppName: string;
    readonly searchTerm: string;
    readonly selectedItem: UiItem | null;

    // actions
    readonly onSearchTermChanged: (searchTerm: string) => void;
}

type DefaultProps = Readonly<Required<{
    configDocAppName: string;
    searchTerm: string;
    selectedItem: UiItem | null;

    onSearchTermChanged: (searchTerm: string) => void;
}>>;

const defaultProps: DefaultProps = {
    configDocAppName: "app name",
    searchTerm: "",
    selectedItem: null,

    // no ops actions
    onSearchTermChanged: () => { return; }
};

//
// Class
//
export default class AppHeader extends React.PureComponent<IAppHeaderProps, any> {
    public static readonly defaultProps = defaultProps;

    public render() {
        const searchTermChangedHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
            this.props.onSearchTermChanged(event.currentTarget.value);
        }
        return <header className="app-header">
            <div className="app-header--icon">
                <FontAwesomeIcon icon={appIcon} />
            </div>
            <div className="app-header--title">
                Config Doc for: {this.props.configDocAppName}
            </div>
            <div className="app-header--selected-item">
                {this.props.selectedItem !== null ?
                    <div>Selected: <UiItemView item={this.props.selectedItem} /></div> :
                    <span>Select a property or namespace!</span>
                }
            </div>
            <div className="app-header--filters">
                <input title="search" onChange={searchTermChangedHandler} placeholder="search ..." />
            </div>
        </header>;
    }

}
