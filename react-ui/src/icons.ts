import {IconDefinition} from '@fortawesome/fontawesome-svg-core'
import {fasIcon} from "./font-awesome";

import {UiItemType} from "./Domain/Ui/UiItem";
import {UiSidebarViewMode} from "./Redux/Store/Ui/UiStore";

export const appIcon: IconDefinition = fasIcon('info');
export const namespaceIcon: IconDefinition = fasIcon('cube');
export const apiPropertyIcon: IconDefinition = fasIcon('clipboard-check');
export const implementationPropertyIcon: IconDefinition = fasIcon('exclamation-circle');
export const unknownIcon: IconDefinition = fasIcon('question');
export const noPropertiesIcon: IconDefinition = fasIcon('radiation');

// view modes
export const sidebarViewModeDefaultIcon: IconDefinition = fasIcon('file-alt');
export const sidebarViewModeTreeIcon: IconDefinition = fasIcon('tree');
export const sidebarViewModeGroupedIcon: IconDefinition = fasIcon('sitemap');
export const sidebarViewModeFlatIcon: IconDefinition = fasIcon('stream');

export function iconForItem(type: UiItemType) {
    switch (type) {
        case UiItemType.NAMESPACE:
            return namespaceIcon;
        case UiItemType.PROPERTY_API:
            return apiPropertyIcon;
        case UiItemType.PROPERTY_IMPLEMENTATION:
            return implementationPropertyIcon;
        default:
            return unknownIcon;
    }
}

export function iconForSidebarViewMode(viewMode: UiSidebarViewMode) {
    switch (viewMode) {
        case UiSidebarViewMode.DEFAULT:
            return sidebarViewModeDefaultIcon;
        case UiSidebarViewMode.TREE:
            return sidebarViewModeTreeIcon;
        case UiSidebarViewMode.GROUPED:
            return sidebarViewModeGroupedIcon;
        case UiSidebarViewMode.FLAT:
            return sidebarViewModeFlatIcon;
        default:
            return unknownIcon;
    }
}
