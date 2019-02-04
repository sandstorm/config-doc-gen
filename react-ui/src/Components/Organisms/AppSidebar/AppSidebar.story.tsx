import {action} from '@storybook/addon-actions';
import {storiesOf} from '@storybook/react';

import * as React from 'react';

import AppSidebar from '.';
import {UiSidebarViewMode} from "../../../Redux/Store/Ui/UiStore";

storiesOf('AppSidebar', module)
    .add('default view', () => (
        <AppSidebar viewMode={UiSidebarViewMode.DEFAULT} onViewModeSelected={action('view mode selected')}/>
    ))
    .add('flat view', () => (
        <AppSidebar viewMode={UiSidebarViewMode.FLAT} onViewModeSelected={action('view mode selected')}/>
    ))
    .add('grouped view', () => (
        <AppSidebar viewMode={UiSidebarViewMode.GROUPED} onViewModeSelected={action('view mode selected')}/>
    ))
    .add('tree view', () => (
        <AppSidebar viewMode={UiSidebarViewMode.TREE} onViewModeSelected={action('view mode selected')}/>
    ))
;
