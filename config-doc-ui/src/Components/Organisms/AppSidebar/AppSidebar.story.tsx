import { storiesOf } from '@storybook/react';
import * as React from 'react';
import AppSidebar from '.';
import {UiSidebarViewMode} from "../../../Redux/Store/Ui/UiStore";

storiesOf('AppSidebar', module)
  .add('default view', () => (
    <AppSidebar viewMode={UiSidebarViewMode.DEFAULT} />
  ))
  .add('flat view', () => (
    <AppSidebar viewMode={UiSidebarViewMode.FLAT} />
  ))
  .add('grouped view', () => (
    <AppSidebar viewMode={UiSidebarViewMode.GROUPED} />
  ))
  .add('tree view', () => (
    <AppSidebar viewMode={UiSidebarViewMode.TREE} />
  ))
;
