import {Accessibility} from "../Accessibility";
import Namespace from "../Namespace";
import Property from "../Property";

// tslint:disable-next-line:interface-name
export interface UiItem {
    readonly identifier: string;
    readonly label: string;
    readonly type: UiItemType;
}

export function uiItemFromProperty(property: Property, showQualifiedName: boolean): UiItem {
    return {
        identifier: property.qualifiedName,
        label: showQualifiedName ? property.qualifiedName : property.name,
        type: property.accessibility === Accessibility.API ? UiItemType.PROPERTY_API : UiItemType.PROPERTY_IMPLEMENTATION,
    }
}

export function uiItemFromNamespace(namespace: Namespace): UiItem {
    return {
        identifier: namespace.name,
        label: namespace.name,
        type: UiItemType.NAMESPACE,
    }
}

export enum UiItemType {
    PROPERTY_API,
    PROPERTY_IMPLEMENTATION,
    NAMESPACE,
    UNKNOWN,
}
