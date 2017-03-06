import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import PieRadarChartBase from './PieRadarChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'PieChart',
  propTypes: {
    ...PieRadarChartBase.propTypes,

    drawEntryLabels: PropTypes.bool,
    usePercentValues: PropTypes.bool,

    centerText: PropTypes.string,
    centerTextRadiusPercent: PropTypes.number,
    holeRadius: PropTypes.number,
    holeColor: PropTypes.string,
    transparentCircleRadius: PropTypes.number,
    transparentCircleColor: PropTypes.string,
    transparentCircleAlpha: PropTypes.number,

    entryLabelColor: PropTypes.string,
    entryLabelTextSize: PropTypes.number,

    maxAngle: PropTypes.number,

    // TODO PieChart should have only one dataset
    data: PropTypes.shape({
      dataSets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(
          PropTypes.oneOfType([
            PropTypes.shape({
              value: PropTypes.number.isRequired,
              label: PropTypes.string
            }),
            PropTypes.number
          ])
        ),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,

          sliceSpace: PropTypes.number,
          selectionShift: PropTypes.number
        })
      })),

    })
  }
};

export default requireNativeComponent('MPAndroidPieChart', iface);
