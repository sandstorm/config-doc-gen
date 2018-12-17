export interface UiItem {
    readonly identifier: string;
    readonly label: string;
    readonly type: UiItemType;
}

export enum UiItemType {
    PROPERTY_API,
    PROPERTY_IMPLEMENTATION,
    NAMESPACE,
    UNKNOWN
}
