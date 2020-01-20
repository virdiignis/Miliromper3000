from django.contrib.auth.models import User
from rest_framework.serializers import HyperlinkedModelSerializer, ModelSerializer


class UserSerializer(ModelSerializer):
    class Meta:
        model = User
        fields = "__all__"
